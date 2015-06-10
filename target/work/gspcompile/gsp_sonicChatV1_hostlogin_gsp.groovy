import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sonicChatV1_hostlogin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/host/login.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('resource','g',3,['dir':("css"),'file':("menuStyle.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(color)
printHtmlPart(5)
expressionOut.print(message)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('textField','g',51,['name':("username"),'type':("test"),'style':("width: 100%; margin: auto;")],-1)
printHtmlPart(8)
invokeTag('textField','g',57,['name':("password"),'type':("test"),'style':("width: 100%; margin: auto; ")],-1)
printHtmlPart(9)
invokeTag('actionSubmit','g',63,['value':("Login"),'action':("loginEmployee"),'style':("margin-bottom: 15px;"),'class':("MessageOK")],-1)
printHtmlPart(10)
})
invokeTag('form','g',66,['controller':("Host"),'action':("loginEmployee")],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',100,['style':("")],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433812243149L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
