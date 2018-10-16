package ro.ubb.downWork.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewPersonDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDto;
import ro.ubb.downWork.apigateway.mapper.NewPersonMapper;
import ro.ubb.downWork.apigateway.mapper.PersonMapper;
import ro.ubb.downWork.profilemicro.dto.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Service
public class PersonServiceImpl implements PersonService{

    private final String personServiceUrl = "http://localhost:9998/profilemicro/person/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NewPersonMapper newPersonMapper;

    @Override
    public ApiGatewayNewPersonDto create(ApiGatewayNewPersonDto newPersonDto) {
        newPersonDto.setPassword(passwordEncoder.encode(newPersonDto.getPassword()));
        NewPersonDto dto = restTemplate
                .postForEntity(personServiceUrl + "create", newPersonMapper.toInternal(newPersonDto), NewPersonDto.class).getBody();
        return newPersonMapper.toExternal(dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(this.personServiceUrl + "delete/" + id);
    }

    @Override
    public Set<ApiGatewayPersonDto> findAll() {
        PersonDtoList personDtoList = restTemplate.getForEntity(this.personServiceUrl + "getall", PersonDtoList.class).getBody();
        return personMapper.toExternals(personDtoList.getPersonDtoList());
    }

    @Override
    public ApiGatewayPersonDto update(ApiGatewayPersonDto personDto) {
        if (!Objects.equals(findByUsername(personDto.getUsername()).getPassword(), personDto.getPassword())) {
            personDto.setPassword(passwordEncoder.encode(personDto.getPassword()));
        }
        HttpEntity<ApiGatewayNewPersonDto> updatedInstance = new HttpEntity<>(personDto);
        ApiGatewayPersonDto apiGatewayPersonDto = personMapper.toExternal(
                restTemplate.exchange(this.personServiceUrl + "update", HttpMethod.PUT, updatedInstance, PersonDto.class).getBody());
        return apiGatewayPersonDto;
    }

    @Override
    public ApiGatewayPersonDto findById(Long id) {
        PersonDto dto = restTemplate.postForEntity(this.personServiceUrl + "getbyid", id, PersonDto.class).getBody();
        return personMapper.toExternal(dto);
    }

    @Override
    public ApiGatewayPersonDto findByUsername(String username) {
        PersonDto dto = restTemplate.postForEntity(this.personServiceUrl + "getbyusername", username, PersonDto.class).getBody();
        return personMapper.toExternal(dto);
    }

    @Override
    public void resetPassword(String mail) {
        restTemplate.postForEntity(this.personServiceUrl + "reset/" + mail + "/", "", Void.class);
    }

    @Override
    public void changePassword(String token, String password) {
        restTemplate.postForEntity(this.personServiceUrl + "changepassword/" + token + "?password=" + password, "", Void.class);
    }

    @Override
    public NotificationDtoList getNotifications(String username) {
        return restTemplate.getForEntity(this.personServiceUrl+"getnotifbyusername/"+username, NotificationDtoList.class).getBody();
    }

    @Override
    public ReviewDtoList getReviews(String username) {
        return restTemplate.getForEntity(this.personServiceUrl+"getreviewsbyusername/"+username, ReviewDtoList.class).getBody();
    }

    @Override
    public void addReview(String username, ReviewDto reviewDto) {
        restTemplate.postForEntity(this.personServiceUrl + "addreview/" + username, reviewDto, Void.class);
    }

    @Override
    public Map<String, String> haveCollaborated(String reviewer, String reviewed) {
        Map<String, String> res = (Map<String, String>)restTemplate.getForEntity(this.personServiceUrl + "collaborated/" + reviewer + "/" + reviewed, Map.class).getBody();
        return res;
    }

    public NotificationDtoList updateNotifications(String username, String usernameBody) {
        HttpEntity<String> updatedInstance = new HttpEntity<>(usernameBody);
        return restTemplate.exchange(this.personServiceUrl+"updatenotifbyusername/"+username, HttpMethod.PUT, updatedInstance, NotificationDtoList.class).getBody();
    }
}
