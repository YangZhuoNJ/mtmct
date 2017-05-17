package tomcat.base;

import org.apache.catalina.connector.http.SocketInputStream;
import org.apache.catalina.util.ParameterMap;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpRequest implements HttpServletRequest {

    private SocketInputStream sis = null;
    private String queryString = null;
    private boolean requestedSessionURL;
    private String sessionId = null;


    protected HashMap<String, Object> headers = new HashMap<String, Object>();          //http headers
    protected ArrayList cookies = new ArrayList();                                      //web cookies
    protected ParameterMap parameters = null;                                           //request parameters
    private String uri = null;
    private String method = null;
    private String protocol = null;



    public void setRequestURI(String normalizeUri) {
        this.uri = normalizeUri;
    }

    public HttpRequest(SocketInputStream sis) {
        this.sis = sis;
    }

    public void setRequestedSessionURL(boolean b) {
        this.requestedSessionURL = b;
    }

    public void setQueryString(String query) {
        this.queryString = query;
    }

    public void setRequestedSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    //auto generate method
    public String getAuthType() {
        return null;
    }

    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    public long getDateHeader(String s) {
        return 0;
    }

    public String getHeader(String s) {
        return null;
    }

    public Enumeration<String> getHeaders(String s) {
        return null;
    }

    public Enumeration<String> getHeaderNames() {
        return null;
    }

    public int getIntHeader(String s) {
        return 0;
    }

    public String getMethod() {
        return null;
    }

    public String getPathInfo() {
        return null;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getContextPath() {
        return null;
    }

    public String getQueryString() {
        return null;
    }

    public String getRemoteUser() {
        return null;
    }

    public boolean isUserInRole(String s) {
        return false;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public String getRequestedSessionId() {
        return null;
    }

    public String getRequestURI() {
        return null;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public String getServletPath() {
        return null;
    }

    public HttpSession getSession(boolean b) {
        return null;
    }

    public HttpSession getSession() {
        return null;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    public void login(String s, String s1) throws ServletException {

    }

    public void logout() throws ServletException {

    }

    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    public Object getAttribute(String s) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String s) {
        return null;
    }

    public Enumeration<String> getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String s) {
        return new String[0];
    }

    public Map<String, String[]> getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String s, Object o) {

    }

    public void removeAttribute(String s) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration<Locale> getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public String getRealPath(String s) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

    public AsyncContext getAsyncContext() {
        return null;
    }

    public DispatcherType getDispatcherType() {
        return null;
    }


    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocal) {
        this.protocol = protocal;
    }
}
