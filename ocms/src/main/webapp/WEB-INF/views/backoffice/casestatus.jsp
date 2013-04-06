<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1>
    <spring:message code="view.backoffice.casestatus.title" />
</h1>

<div id="page-container" class="resize">
    <div id="page-content-inner" class="resize">
        <br clear="all" />
        <div>

            <div id="error">
                <spring:hasBindErrors name="botype">
                    <font color="red"> <c:forEach items="${errors.allErrors}"
                            var="error">
                            <spring:message code="${error.code}"
                                text="${error.defaultMessage}" />
                            <br />
                        </c:forEach>
                    </font>
                </spring:hasBindErrors>
            </div>
            <form:form method="post"
                action="/backoffice/casestatus" id="backofficeForm"
                modelAttribute="botype">

                <div class="error" style="display: none;">
                    <img src="resources/images/warning.gif" alt="Warning!" width="24"
                        height="24" style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
                        clear="all" />
                </div>


                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="label"><label for="caseStatusName">Case Status Name:</label></td>
                        <td class="field"><form:input path="name" type="text"
                                class="required" value="" tabindex="1" /></td>
                    </tr>
                         <tr>
                        <td class="label"><label for="caseStatusDescription"> Case Status Description:</label></td>
                        <td class="field"><form:input path="description" type="textarea"
                                class="required" value="" tabindex="2" /></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td>
                            <div class="buttonSubmit">
                                <span></span> <input class="formButton" type="submit"
                                    value="Add Case Status" style="width: 140px" tabindex="3" />
                            </div>

                        </td>

                    </tr>
                </table>
                <br />
                <br />
            </form:form>
            <!-- </form> -->
            <br clear="all" />


        </div>



        </p>

    </div>
</div>