import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sonicChatV1_userDataAccessmessages_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userDataAccess/messages.gsp" }
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
expressionOut.print(resource(dir:'images',file:'sonicLogo.png'))
printHtmlPart(5)
for( message in (messages) ) {
printHtmlPart(6)
expressionOut.print(message.messageID)
printHtmlPart(7)
if(true && (message.status == 'New')) {
printHtmlPart(8)
}
else if(true && (message.status == 'Read')) {
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(message.status)
printHtmlPart(11)
expressionOut.print(message.name)
printHtmlPart(12)
expressionOut.print(message.email)
printHtmlPart(13)
invokeTag('formatDate','g',52,['format':("MM-dd-yyyy"),'date':(message.date)],-1)
printHtmlPart(14)
expressionOut.print(message.message)
printHtmlPart(15)
expressionOut.print(message.messageID)
printHtmlPart(16)
}
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',102,['style':("")],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1438572693585L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
