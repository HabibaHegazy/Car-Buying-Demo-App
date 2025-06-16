package com.carbuying.car_buying_app.services.inspection;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import org.springframework.stereotype.Component;

@Component
public class AutoCheckAdapter implements InspectionAdapter {

// SAMPLE OF HOW TO CALL AN EXTERNAL API
//    @Override
//    public int requestInspection(Long offerId, String carDetails) {
//        // Define the URL for the external API endpoint
//        String url = "https://api.auto-check-co.com/inspect";  // Replace with the actual API endpoint
//
//        // Create the request payload (if required by the API)
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("offerId", offerId);
//        requestBody.put("carDetails", carDetails);
//
//        try {
//            // Make the HTTP POST request using RestTemplate
//            ResponseEntity<InspectionResponseDto> responseEntity = restTemplate.postForEntity(
//                    url,
//                    requestBody,
//                    InspectionResponseDto.class
//            );
//
//            // Check if the response status code is successful
//            HttpStatusCode httpStatusCode = responseEntity.getStatusCode();
//            if (!httpStatusCode.is2xxSuccessful()) {
//                log.error("Response Status code is : {}, for URL: {}", httpStatusCode, url);
//                throw new InvalidResponseStatusCodeException("Response Status code is : " + httpStatusCode);
//            }
//
//            // Extract and return the inspection score from the response body
//            InspectionResponseDto responseBody = responseEntity.getBody();
//            if (responseBody != null && responseBody.getInspectionScore() != null) {
//                return responseBody.getInspectionScore();
//            } else {
//                log.error("Invalid or missing inspection score in the response body for URL: {}", url);
//                throw new InvalidResponseException("Invalid or missing inspection score in the response");
//            }
//
//        } catch (ClientServer5xxException clientServer5xxException) {
//            log.error("500 Exception received from AUTO_CHECK_CO Service For INSPECTION POST Client:: Error Message: {}, Stacktrace: {}",
//                    clientServer5xxException.getMessage(), clientServer5xxException.getStackTrace());
//            throw clientServer5xxException;
//        } catch (InvalidResponseStatusCodeException | InvalidResponseException exception) {
//            log.error("Response Exception received from AUTO_CHECK_CO Service For INSPECTION POST Client:: Error Message: {}, Stacktrace: {}",
//                    exception.getMessage(), exception.getStackTrace());
//            throw new ClientServer5xxException(exception.getMessage());
//        } catch (Exception exception) {
//            log.error("Exception received from AUTO_CHECK_CO Service For INSPECTION POST Client:: Error Message: {}, Stacktrace: {}",
//                    exception.getMessage(), exception.getStackTrace());
//            throw new RuntimeException("Failed to request inspection", exception);
//        }
//    }

    @Override
    public int requestInspection(Long offerId, String carDetails) {
        // Simulate API call to AUTO_CHECK_CO
        // In real implementation, this would make an HTTP request
        System.out.println("Requesting inspection from AUTO_CHECK_CO for offer: " + offerId);
        System.out.println("Car details: " + carDetails);

        // Simulate inspection score (0-100)
        return (int) (Math.random() * 101);
    }

    @Override
    public InspectionCompany getCompanyType() {
        return InspectionCompany.AUTO_CHECK_CO;
    }

}
