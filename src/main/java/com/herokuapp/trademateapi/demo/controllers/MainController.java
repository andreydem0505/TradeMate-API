package com.herokuapp.trademateapi.demo.controllers;

import com.herokuapp.trademateapi.demo.DataEngine;
import com.herokuapp.trademateapi.demo.EmailMessage;
import com.herokuapp.trademateapi.demo.EmailSender;
import com.herokuapp.trademateapi.demo.Hash;
import com.herokuapp.trademateapi.demo.models.*;
import com.herokuapp.trademateapi.demo.objects.*;
import com.herokuapp.trademateapi.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@RestController
public class MainController {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MerchandiserRepository merchandiserRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private PhotoReportRepository photoReportRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping(value = "/register/company", consumes = "application/json")
    public ResultOfRegistrationObject registerCompany(@Valid @RequestBody NPEObject npeObject) {
        String name = npeObject.getName();
        String password = npeObject.getPassword();
        String email = npeObject.getEmail();

        // checking
        if (companyRepository.findByName(name) != null) {
            return new ResultOfRegistrationObject(
                    "Such company is already exist"
            );
        }

        if (companyRepository.findByEmail(email) != null) {
            return new ResultOfRegistrationObject(
                    "Company with this email is already exist"
            );
        }

        Company company = companyRepository.save(new Company(
                name, Hash.hashPassword(password), email));
        String accessToken = DataEngine.generateAccessToken(company.getId(), "company");
        company.setAccessToken(accessToken);
        companyRepository.save(company);
        return new ResultOfRegistrationObject(
                "Success", accessToken
        );
    }

    @PostMapping(value = "/auth/company", consumes = "application/json")
    public ResultOfAuthObject authCompany(@RequestBody EPObject epObject) {
        Company company = companyRepository.findByEmail(epObject.getEmail());
        if (company == null) {
            return new ResultOfAuthObject("Company with this email wasn't found");
        }
        if (company.getPassword().equals(Hash.hashPassword(epObject.getPassword()))) {
            return new ResultOfAuthObject("Success", company.getName(), company.getAccessToken());
        }
        return new ResultOfAuthObject("Password is incorrect");
    }

    @PutMapping(value="/merchandisers", consumes = "application/json")
    public MessageObject registerMerchandiser(@Valid @RequestBody NPEObject npeObject, HttpServletRequest httpRequest) {
        if (!DataEngine.requestHasHeader(httpRequest, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }
        String companyAccessToken = httpRequest.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(companyAccessToken);
        String name = npeObject.getName();
        String password = npeObject.getPassword();
        String email = npeObject.getEmail();

        // checking
        Merchandiser merchandiser = merchandiserRepository.findByNameAndCompany(name, company);
        if (merchandiser != null) {
            return new MessageObject("Merchandiser with this name is already exist");
        }

        merchandiser = merchandiserRepository.findByEmail(email);
        if (merchandiser != null)
            return new MessageObject("Merchandiser with this email is already exist");

        merchandiser = new Merchandiser(
            name, Hash.hashPassword(password), email);
        merchandiser.setCompany(company);
        merchandiser = merchandiserRepository.save(merchandiser);
        String accessToken = DataEngine.generateAccessToken(merchandiser.getId(), "merchandiser");
        merchandiser.setAccessToken(accessToken);
        merchandiserRepository.save(merchandiser);
        return new MessageObject("Success");
    }

    @PostMapping(value = "/auth/merchandiser", consumes = "application/json")
    public ResultOfAuthObject authMerchandiser(@RequestBody EPObject epObject) {
        Merchandiser merchandiser = merchandiserRepository.findByEmail(epObject.getEmail());
        if (merchandiser == null) {
            return new ResultOfAuthObject("Merchandiser with this email wasn't found");
        }
        if (merchandiser.getPassword().equals(Hash.hashPassword(epObject.getPassword()))) {
            return new ResultOfAuthObject("Success", merchandiser.getName(), merchandiser.getAccessToken());
        }
        return new ResultOfAuthObject("Password is incorrect");
    }

    @PutMapping(value = "/operators", consumes = "application/json")
    public MessageObject registerOperator(@Valid @RequestBody NEObject neObject, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }
        String companyAccessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(companyAccessToken);
        String name = neObject.getName();
        String email = neObject.getEmail();

        // checking
        Operator operator = operatorRepository.findByNameAndCompany(name, company);
        if (operator != null) {
            return new MessageObject("Operator with this name is already exist");
        }

        Operator o = operatorRepository.findByEmail(email);
        if (o != null)
            return new MessageObject("Operator with this email is already exist");

        operator = new Operator(
              name, email
        );
        operator.setCompany(company);
        operatorRepository.save(operator);
        return new MessageObject("Success");
    }

    @GetMapping(value = "/operator")
    public EmailObject getEmailOfOperator(@RequestParam String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new EmailObject("Access token wasn't found");
        }
        String merchandiserAccessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(merchandiserAccessToken);
        Operator operator = operatorRepository.findByNameAndCompany(name, merchandiser.getCompany());
        if (operator != null)
            return new EmailObject("Success", operator.getEmail());

        return new EmailObject("Operator wasn't found");
    }

    @GetMapping(value = "/merchandisers")
    public MerchandisersListObject getListOfMerchandisers(HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MerchandisersListObject("Access token wasn't found");
        }
        String companyAccessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(companyAccessToken);
        if (company == null) {
            return new MerchandisersListObject("Access token is wrong");
        }
        List<MerchandiserDTO> merchandisersDTO = new ArrayList<>();
        List<Merchandiser> merchandisers = company.getMerchandisers();
        merchandisers.sort(Comparator.comparing(Merchandiser::getName));
        for (Merchandiser el : merchandisers) {
            merchandisersDTO.add(new MerchandiserDTO(el.getName(), el.getEmail(), Hash.decodePassword(el.getPassword())));
        }
        return new MerchandisersListObject("Success", merchandisersDTO);
    }

    @GetMapping(value = "/operators")
    public OperatorsListObject getListOfOperators(HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new OperatorsListObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        List<OperatorDTO> operatorsDTO = new ArrayList<>();
        Function<Company, OperatorsListObject> successReturn = company -> {
            List<Operator> operators = company.getOperators();
            operators.sort(Comparator.comparing(Operator::getName));
            for (Operator el : operators) {
                operatorsDTO.add(new OperatorDTO(el.getName(), el.getEmail()));
            }
            return new OperatorsListObject("Success", operatorsDTO);
        };

        switch (accessToken.split("_")[0]) {
            case "company": {
                Company company = companyRepository.findByAccessToken(accessToken);
                if (company != null)
                    return successReturn.apply(company);
            } break;
            case "merchandiser": {
                Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);
                Company company = merchandiser.getCompany();
                if (company != null)
                    return successReturn.apply(company);
            } break;
        }

        return new OperatorsListObject("Access token is wrong");
    }

    @PutMapping(value = "/requests", consumes = "application/json")
    public MessageObject createRequest(@RequestBody RequestEmailObject requestObject, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);

        if (merchandiser == null) {
            return new MessageObject("Merchandiser with this access token wasn't found");
        }

        String operatorEmail = requestObject.getOperatorEmail();
        Operator operator = operatorRepository.findByEmail(operatorEmail);
        if (operator == null) {
            return new MessageObject("Operator with this email wasn't found");
        }

        String subject = requestObject.getSubject();
        String text = requestObject.getText();
        LocalDateTime dateTime = requestObject.getDateTime();

        requestRepository.save(
                new Request(subject, text, dateTime, merchandiser, operator)
        );

        new EmailSender().send(new EmailMessage(operatorEmail, subject, text));

        return new MessageObject("Success");
    }

    @GetMapping(value = "/requests")
    public RequestsListObject getAllRequests(@RequestParam String date, @RequestParam(required = false) String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new RequestsListObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser;

        if (name != null) { // If request has a merchandiser
            Company company = companyRepository.findByAccessToken(accessToken);
            if (company == null)
                return new RequestsListObject("Company with this access token wasn't found");
             // If the company and name was found
            merchandiser = merchandiserRepository.findByNameAndCompany(name, company);
            if (merchandiser == null) {
                return new RequestsListObject("Merchandiser with this name wasn't found");
            }
        } else { // if request does not have a merchandiser
            if (accessToken.startsWith("company")) {
                Company company = companyRepository.findByAccessToken(accessToken);
                if (company == null)
                    return new RequestsListObject("Company with this access token wasn't found");
                LocalDateTime[] range = DataEngine.getRangeFromDate(date);
                List<Request> requests = requestRepository.findAllByDateTimeBetweenOrderByDateTimeDesc(range[0], range[1]);
                List<RequestNameObject> requestObjects = new ArrayList<>();
                for (Request r : requests) {
                    if (r.getMerchandiser().getCompany().equals(company)) {
                        requestObjects.add(new RequestNameObject(
                                r.getSubject(),
                                r.getText(),
                                r.getOperator().getName(),
                                r.getDateTime()));
                    }
                }
                return new RequestsListObject("Success", requestObjects);
            } else {
                merchandiser = merchandiserRepository.findByAccessToken(accessToken);
                if (merchandiser == null) {
                    return new RequestsListObject("Merchandiser with this access token wasn't found");
                }
            }
        }

        LocalDateTime[] range = DataEngine.getRangeFromDate(date);
        List<Request> requests = requestRepository.findAllByDateTimeBetweenAndMerchandiserOrderByDateTimeDesc(range[0], range[1], merchandiser);
        List<RequestNameObject> requestObjects = new ArrayList<>();
        for (Request r : requests) {
            requestObjects.add(new RequestNameObject(
                    r.getSubject(),
                    r.getText(),
                    r.getOperator().getName(),
                    r.getDateTime()));
        }

        return new RequestsListObject("Success", requestObjects);
    }

    @PutMapping(value = "/shops", consumes = "application/json")
    public MessageObject createShop(@RequestBody NameObject nameObject, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(accessToken);

        if (company == null) {
            return new MessageObject("Company with this access token wasn't found");
        }

        if (shopRepository.findByNameAndCompany(nameObject.getName(), company) != null) {
            return new MessageObject("Such shop is already exist");
        }

        shopRepository.save(new Shop(nameObject.getName(), company));
        return new MessageObject("Success");
    }

    @GetMapping(value = "/shops")
    public ShopsListObject getListOfShops(HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new ShopsListObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");

        Company company = null;
        switch (accessToken.split("_")[0]) {
            case "company": {
                company = companyRepository.findByAccessToken(accessToken);
            } break;
            case "merchandiser": {
                company = merchandiserRepository.findByAccessToken(accessToken).getCompany();
            } break;
        }

        if (company == null)
            return new ShopsListObject("Company with this access token wasn't found");

        List<Shop> shops = shopRepository.findAllByCompanyOrderByName(company);
        String[] strings = new String[shops.size()];
        for (int i = 0; i < shops.size(); i++) {
            strings[i] = shops.get(i).getName();
        }
        return new ShopsListObject("Success", strings);
    }

    @PutMapping(value = "/photo_reports")
    public MessageObject createPhotoReport(@RequestBody NameObject nameObject, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);
        if (merchandiser == null)
            return new MessageObject("Such merchandiser wasn't found");
        if (photoReportRepository.findByNameAndMerchandiser(nameObject.getName(), merchandiser) != null)
            return new MessageObject("Photo report with this name is already exist");

        photoReportRepository.save(new PhotoReport(nameObject.getName(), merchandiser));
        return new MessageObject("Success");
    }

    @GetMapping(value = "/photo_reports")
    public ReportsListObject getPhotoReports(HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new ReportsListObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);
        if (merchandiser == null)
            return new ReportsListObject("Merchandiser with this access token wasn't found");
        List<PhotoReport> photoReports = merchandiser.getPhotoReports();
        String[] array = new String[photoReports.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = photoReports.get(i).getName();
        }
        return new ReportsListObject("Success", array);
    }

    @GetMapping(value = "/report/{name}")
    public PhotosListObject getPhotosOfReport(@PathVariable("name") String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new PhotosListObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);
        if (merchandiser == null)
            return new PhotosListObject("Merchandiser with this access token wasn't found");
        PhotoReport photoReport = photoReportRepository.findByNameAndMerchandiser(name, merchandiser);
        List<PhotoDto> list = new ArrayList<>();
        for (Photo photo : photoReport.getPhotos()) {
            list.add(new PhotoDto(photo.getId(), photo.getByteCode()));
        }
        return new PhotosListObject("Success", list);
    }

    @PutMapping(value = "/report/{name}")
    public MessageObject putPhoto(@PathVariable("name") String name, @RequestBody PhotoObject photoObject, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);

        if (merchandiser == null)
            return new MessageObject("Such merchandiser wasn't found");

        PhotoReport photoReport = photoReportRepository.findByNameAndMerchandiser(name, merchandiser);
        if (photoReport == null)
            return new MessageObject("Photo report with this name wasn't found");

        photoRepository.save(new Photo(photoObject.getByteCode(), photoReport));

        return new MessageObject("Success");
    }

    @GetMapping(value = "/report/{name}/send")
    public MessageObject sendPhotoReport(@PathVariable("name") String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);

        if (merchandiser == null)
            return new MessageObject("Such merchandiser wasn't found");

        PhotoReport photoReport = photoReportRepository.findByNameAndMerchandiser(name, merchandiser);
        if (photoReport == null)
            return new MessageObject("Photo report with this name wasn't found");

        List<byte[]> bytes = new ArrayList<>();
        List<Photo> photos = photoReport.getPhotos();
        if (photos.size() > 0) {
            for (Photo photo : photos) {
                bytes.add(photo.getByteCode());
            }
            new EmailSender().sendPhotos(bytes, merchandiser.getEmail(), name);
            return new MessageObject("Success");
        }
        return new MessageObject("No photos to send");
    }

    @DeleteMapping(value = "/report/{name}")
    public MessageObject deletePhotoReport(@PathVariable("name") String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);

        if (merchandiser == null)
            return new MessageObject("Such merchandiser wasn't found");

        PhotoReport photoReport = photoReportRepository.findByNameAndMerchandiser(name, merchandiser);
        if (photoReport == null)
            return new MessageObject("Photo report with this name wasn't found");

        photoReportRepository.delete(photoReport);
        return new MessageObject("Success");
    }

    @DeleteMapping(value = "/report/{name}/photo/{id}")
    public MessageObject deletePhoto(@PathVariable("name") String name, @PathVariable("id") long id, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Merchandiser merchandiser = merchandiserRepository.findByAccessToken(accessToken);

        if (merchandiser == null)
            return new MessageObject("Such merchandiser wasn't found");

        PhotoReport photoReport = photoReportRepository.findByNameAndMerchandiser(name, merchandiser);
        if (photoReport == null)
            return new MessageObject("Photo report with this name wasn't found");

        Photo photo = photoRepository.findByIdAndPhotoReport(id, photoReport);
        if (photo == null)
            return new MessageObject("Photo with this id is not in this report");

        photoRepository.delete(photo);
        return new MessageObject("Success");
    }

    @DeleteMapping(value = "/operator")
    public MessageObject deleteOperator(@RequestParam String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(accessToken);

        if (company == null)
            return new MessageObject("Such company wasn't found");

        Operator operator = operatorRepository.findByNameAndCompany(name, company);
        if (operator == null)
            return new MessageObject("Operator with this name wasn't found");

        operatorRepository.delete(operator);
        return new MessageObject("Success");
    }

    @DeleteMapping(value = "/merchandiser")
    public MessageObject deleteMerchandiser(@RequestParam String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(accessToken);

        if (company == null)
            return new MessageObject("Such company wasn't found");

        Merchandiser merchandiser = merchandiserRepository.findByNameAndCompany(name, company);
        if (merchandiser == null)
            return new MessageObject("Merchandiser with this name wasn't found");

        merchandiserRepository.delete(merchandiser);
        return new MessageObject("Success");
    }

    @DeleteMapping(value = "/shop")
    public MessageObject deleteShop(@RequestParam String name, HttpServletRequest request) {
        if (!DataEngine.requestHasHeader(request, "access_token")) {
            return new MessageObject("Access token wasn't found");
        }

        String accessToken = request.getHeader("access_token");
        Company company = companyRepository.findByAccessToken(accessToken);

        if (company == null)
            return new MessageObject("Such company wasn't found");

        Shop shop = shopRepository.findByNameAndCompany(name, company);
        if (shop == null)
            return new MessageObject("Such shop wasn't found");

        shopRepository.delete(shop);
        return new MessageObject("Success");
    }
}
