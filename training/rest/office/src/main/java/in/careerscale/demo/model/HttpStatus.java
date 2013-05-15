/**
 * This code is provided by Careerscale IT Consulting LLP, Hyderabad, India. 
 * visit us at http://careerscale.in
 * If you are looking for training(online/corporate, please get in touch with us.
 * Contact us at info@careerscale.in
 */
package in.careerscale.demo.model;

public enum HttpStatus {
    OK(200, "OK"), CREATED(201, "Created"), NO_CONTENT(204, "No Content"), PARTIAL_CONTENT(206, "Partial Content"), REDIRECT(301,
            "Moved Permanently"), NOT_MODIFIED(304, "Not Modified"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401,
            "Unauthorized"), FORBIDDEN(403, "Forbidden"), NOT_FOUND(404, "Not Found"),PRE_CONDITION(412,"Precondition Failed"), RANGE_NOT_SATISFIABLE(416,
            "Requested Range Not Satisfiable"), INTERNAL_ERROR(500, "Internal Server Error");
    private int requestStatus;
    private String descr;

    HttpStatus(int requestStatus, String descr) {
        this.requestStatus = requestStatus;
        this.descr = descr;
    }

    public int getRequestStatus() {
        return this.requestStatus;
    }

    public String getDescription() {
        return "" + this.requestStatus + " " + descr;
    }
}

