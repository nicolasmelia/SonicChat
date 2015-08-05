import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_sonicChatV1_userDataAccessmainDash_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userDataAccess/mainDash.gsp" }
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
expressionOut.print(session.displayName)
printHtmlPart(6)
for( tile in (tiles) ) {
printHtmlPart(7)
expressionOut.print(tile.tileViewController)
printHtmlPart(8)
expressionOut.print(tile.tileID)
printHtmlPart(9)
expressionOut.print(tile.tileDisplayName)
printHtmlPart(10)
}
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',99,['style':("")],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1438572261083L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
