package ro.ubb.downWork.apigateway.service;

import ro.ubb.downWork.apigateway.dto.ApiGatewayNewPersonDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDto;
import ro.ubb.downWork.profilemicro.dto.NotificationDtoList;
import ro.ubb.downWork.profilemicro.dto.ReviewDto;
import ro.ubb.downWork.profilemicro.dto.ReviewDtoList;

import java.util.Map;
import java.util.Set;

/**
 * Created by langchristian96 on 10/20/2017.
 */
public interface PersonService {

    ApiGatewayNewPersonDto create(ApiGatewayNewPersonDto newPersonDto);

    void delete(Long id);

    Set<ApiGatewayPersonDto> findAll();

    ApiGatewayPersonDto update(ApiGatewayPersonDto personDto);

    ApiGatewayPersonDto findById(Long id);

    ApiGatewayPersonDto findByUsername(String username);

    void resetPassword(String mail);

    void changePassword(String token, String password);

    NotificationDtoList getNotifications(String username);

    ReviewDtoList getReviews(String username);

    void addReview(String username, ReviewDto reviewDto);

    Map<String, String> haveCollaborated(String reviewer, String reviewed);

    NotificationDtoList updateNotifications(String username, String usernameBody);
}
