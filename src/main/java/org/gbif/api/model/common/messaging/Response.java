/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.model.common.messaging;

import com.google.common.base.Objects;

public class Response<T> {

  private String messageKey;
  private String message;
  private Status status;
  private Integer statusCode;
  private T content;

  private Throwable rootException;

  public Response() {
  }

  public Response(Response<T> response) {
    message = response.message;
    messageKey = response.messageKey;
    status = response.status;
    statusCode = response.statusCode;
  }

  /**
   * Constructor using status only.
   */
  public Response(Status status) {
    this.status = status;
  }

  public Response(Status status, String messageKey) {
    this.status = status;
    this.messageKey = messageKey;
  }

  public Response(Status status, T content, String messageKey) {
    this.status = status;
    this.content = content;
    this.messageKey = messageKey;
  }

  /**
   * @return the content
   */
  public T getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(T content) {
    this.content = content;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the messageKey
   */
  public String getMessageKey() {
    return messageKey;
  }

  /**
   * @param messageKey the messageKey to set
   */
  public void setMessageKey(String messageKey) {
    this.messageKey = messageKey;
  }

  /**
   * @return rootException when the response contains an error
   */
  public Throwable getRootException() {
    return rootException;
  }

  /**
   * Sets the root exception when is error response.
   */
  public void setRootException(Throwable rootException) {
    this.rootException = rootException;
  }

  /**
   * @return the status
   */
  public Status getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * @return the statusCode
   */
  public Integer getStatusCode() {
    return statusCode;
  }

  /**
   * @param statusCode the statusCode to set
   */
  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public void copyTo(Response<?> response) {
    response.setStatus(status);
    response.setStatusCode(statusCode);
    response.setMessage(message);
    response.setMessageKey(messageKey);
    response.setRootException(response.getRootException());
  }

  public String statusReason() {
    if (statusCode != null) {
      return StatusCode.valueOf(statusCode).getReason();
    }
    return null;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("messageKey", messageKey)
      .add("message", message)
      .add("status", status)
      .add("statusCode", statusCode)
      .add("content", content)
      .add("rootException", rootException)
      .toString();
  }

  public enum Status {

    OK, ERROR

  }

  public enum StatusCode {

    OK(200, "The request was accepted and the response entity-body contains the resource representation, "
      + "if any is applicable"), CREATED(201,
      "The request was accepted and resulted in the creation of a new resource. The response Location header contains"
        + " the created resource URI"), ACCEPTED(202,
      "The client request has been accepted but is not handled in real time. The response Location header contains "
        + "the URI of a resource which can be used to check for progress"), NO_CONTENT(204,
      "The client request was accepted but no resource representation is sent in response. A GET, POST, "
        + "PUT or DELETE might typically warrant this response."), MOVED_PERMANENTLY(301,
      "Sent when a client triggers an action that causes the URI of a resource change, "
        + "or if an old URI was requested. An example could be an update to a resource that causes a version "
        + "increment"), SEE_OTHER(303,
      "The request has been processed, and the client is recommended to see another resource, "
        + "which is likely to be the resource they are seeking. This is used only for POST, "
        + "PUT or DELETE when the result might be a resource of interest. For the PortalAPI a GET will always return "
        + "a 307 instead of a 303."), NOT_MODIFIED(304,
      "Issued when the client provided the If-Modified-Since header and the representation has not changed. No "
        + "response entity-body will be given."), TEMPORARY_REDIRECT(307,
      "The request has not been processed, and the client is recommended to see another resource, "
        + "which is likely to be the resource they are seeking. This is returned should a client request a resource "
        + "in the canonical form (e.g. without a version number), which has a default representation as another "
        + "resource (e.g. the latest version)."), BAD_REQUEST(400,
      "There was a problem on the client side, and further information, if any, is available will be given in the "
        + "response entity-body"), UNAUTHORIZED(401,
      "The proper authentication credentials were not supplied to operate on the protected resource. Note that 403 "
        + "will be used when credentials are supplied and correct, but still disallowed"), FORBIDDEN(403,
      "The authentication credentials supplied were correct, but do not allow operation on the protected resource"),
    NOT_FOUND(404, "The server does not know the resource being requested"), CONFLICT(409,
      "Indicates that the operation was not accepted since it would leave one or more resources in an inconsistent "
        + "state. An example could be the deletion of a user account, which is referenced by annotations made by the "
        + "user."), INTERNAL_SERVER_ERROR(500,
      "There was a problem on the server side, and diagnostic information, if any, "
        + "is available in the response entity-body"), SERVICE_UNAVAILABLE(503,
      "The server is responding, but the underlying systems are not operating correctly, "
        + "such as through resource starvation while under heavy load. A Retry-After header will be provided to "
        + "indicate the period at which the server suggest the client try again "), SERVER_UNAVAILABLE(999,
      "The server is not responding or is too slow");

    private Integer code;

    private String reason;

    public static StatusCode valueOf(int statusCode) {
      for (StatusCode value : StatusCode.values()) {
        if (value.getCode() == statusCode) {
          return value;
        }
      }
      return null;
    }

    StatusCode(int code, String reason) {
      this.code = code;
      this.reason = reason;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
      return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
      this.code = code;
    }

    /**
     * @return the reason
     */
    public String getReason() {
      return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
      this.reason = reason;
    }

  }

  /**
   * Builder for Response objects.
   * Source code generated using: http://code.google.com/p/bpep/.
   *
   * @param <T> type of the content field
   */
  public static class Builder<T> {

    private String messageKey;
    private String message;
    private Status status;
    private Integer statusCode;
    private T content;
    private Throwable rootException;

    public Response<T> build() {
      Response<T> response = new Response<T>();
      response.messageKey = messageKey;
      response.message = message;
      response.status = status;
      response.statusCode = statusCode;
      response.content = content;
      response.rootException = rootException;
      return response;
    }

    public Builder<T> content(T content) {
      this.content = content;
      return this;
    }

    /**
     * Creates an builder using/copying a response object.
     */
    public Builder<T> fromResponse(Response<?> otherResponse) {
      return status(otherResponse.getStatus()).statusCode(otherResponse.getStatusCode())
        .message(otherResponse.getMessage())
        .messageKey(otherResponse.getMessageKey())
        .rootException(otherResponse.getRootException());
    }

    public Builder<T> message(String message) {
      this.message = message;
      return this;
    }

    public Builder<T> messageKey(String messageKey) {
      this.messageKey = messageKey;
      return this;
    }

    public Builder<T> rootException(Throwable rootException) {
      this.rootException = rootException;
      return this;
    }

    public Builder<T> status(Status status) {
      this.status = status;
      return this;
    }

    public Builder<T> statusCode(Integer statusCode) {
      this.statusCode = statusCode;
      return this;
    }

  }

}
