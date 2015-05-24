import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sonicChatV1_hostChatDashboard_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/host/ChatDashboard.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('resource','g',5,['dir':("js"),'file':("jquery.gracefulWebSocket.js")],-1)
printHtmlPart(2)
invokeTag('resource','g',6,['dir':("js"),'file':("ChatServiceHost.js")],-1)
printHtmlPart(3)
invokeTag('resource','g',7,['dir':("css"),'file':("host.css")],-1)
printHtmlPart(4)
invokeTag('resource','g',8,['dir':("css"),'file':("menuStyle.css")],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(session.displayName)
printHtmlPart(8)
expressionOut.print(resource(dir:'images',file:'blankavatar.png'))
printHtmlPart(9)
expressionOut.print(resource(dir:'images',file:'searchIcon.png'))
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',112,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1432413727933L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
