package com.spring.learnreactivespring.routers;

import com.spring.learnreactivespring.handlers.TaskHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class TaskRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(path = "/task"
                    , produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    operation = @Operation(operationId = "createTask", responses = {
                            @ApiResponse(responseCode = "200", description = "Created Successfully"),
                            @ApiResponse(responseCode = "400", description = "Invalid Request"),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error")}))
            ,@RouterOperation(path = "/allTask"
                    , produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.GET,
                    operation = @Operation(operationId = "getAllTask", responses = {
                            @ApiResponse(responseCode = "200", description = "Return All Task Successfully"),
                            @ApiResponse(responseCode = "400", description = "Invalid Request"),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error")}))
            ,@RouterOperation(path = "/task/{taskId}"
            , produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            operation = @Operation(operationId = "getTask", responses = {
                    @ApiResponse(responseCode = "200", description = "Return Task Successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")})),
            @RouterOperation(path = "/task/{taskId}"
            , produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT,
            operation =@Operation(operationId = "updateTask", responses = {
                    @ApiResponse(responseCode = "200", description = "Task Updated Successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")}))
    })
    public RouterFunction<ServerResponse> route(TaskHandler taskHandler){
     return RouterFunctions.route(POST("/task").and(accept(MediaType.APPLICATION_JSON)),taskHandler::createTask)
             .andRoute(GET("/task").and(accept(MediaType.APPLICATION_JSON)),taskHandler::getAllTask)
             .andRoute(GET("/task/{taskId}").and(accept(MediaType.APPLICATION_JSON)),taskHandler::getTask)
             .andRoute(PUT("/task/{taskId}").and(accept(MediaType.APPLICATION_JSON)),taskHandler::updateTask);
    }
}
