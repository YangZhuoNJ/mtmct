package tomcat.base;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpResponseFacade implements HttpServletResponse {

    private HttpResponse response = null;

    public HttpResponseFacade(HttpResponse response) {
        this.response = response;
    }

    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public boolean containsHeader(String s) {
        return response.containsHeader(s);
    }

    public String encodeURL(String s) {
        return response.encodeURL(s);
    }

    public String encodeRedirectURL(String s) {
        return response.encodeRedirectURL(s);
    }

    public String encodeUrl(String s) {
        return response.encodeUrl(s);
    }

    public String encodeRedirectUrl(String s) {
        return response.encodeRedirectUrl(s);
    }

    public void sendError(int i, String s) throws IOException {
        response.sendError(i, s);
    }

    public void sendError(int i) throws IOException {
        response.sendError(i);
    }

    public void sendRedirect(String s) throws IOException {
        response.sendRedirect(s);
    }

    public void setDateHeader(String s, long l) {
        response.setDateHeader(s, l);
    }

    public void addDateHeader(String s, long l) {
        response.addDateHeader(s, l);
    }

    public void setHeader(String s, String s1) {
        response.setHeader(s, s1);
    }

    public void addHeader(String s, String s1) {
        response.addHeader(s, s1);
    }

    public void setIntHeader(String s, int i) {
        response.setIntHeader(s, i);
    }

    public void addIntHeader(String s, int i) {
        response.addIntHeader(s, i);
    }

    public void setStatus(int i) {
        response.setStatus(i);
    }

    public void setStatus(int i, String s) {
        response.setStatus(i, s);
    }

    public int getStatus() {
        return response.getStatus();
    }

    public String getHeader(String s) {
        return response.getHeader(s);
    }

    public Collection<String> getHeaders(String s) {
        return response.getHeaders(s);
    }

    public Collection<String> getHeaderNames() {
        return response.getHeaderNames();
    }

    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    public String getContentType() {
        return response.getContentType();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    public void setCharacterEncoding(String s) {
        response.setCharacterEncoding(s);
    }

    public void setContentLength(int i) {
        response.setContentLength(i);
    }

    public void setContentType(String s) {
        response.setContentType(s);
    }

    public void setBufferSize(int i) {
        response.setBufferSize(i);
    }

    public int getBufferSize() {
        return response.getBufferSize();
    }

    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }

    public void resetBuffer() {
        response.resetBuffer();
    }

    public boolean isCommitted() {
        return response.isCommitted();
    }

    public void reset() {
        response.reset();
    }

    public void setLocale(Locale locale) {
        response.setLocale(locale);
    }

    public Locale getLocale() {
        return response.getLocale();
    }
}
