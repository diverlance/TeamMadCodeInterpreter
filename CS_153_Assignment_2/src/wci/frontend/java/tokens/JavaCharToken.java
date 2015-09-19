package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;

/**
 *
 * @author Ying Yau
 */
public class JavaCharToken extends JavaToken {
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCharToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java word token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = nextChar();
        textBuffer.append('\'');
        if (currentChar == '\\'){
            textBuffer.append(currentChar);
            currentChar = nextChar();
            if (currentChar == 'n'){
                type = BLANK_LINE;
                textBuffer.append(currentChar);
            }
            else if (currentChar == 't'){
                type = TAB;
                textBuffer.append(currentChar);
            }
            else if (currentChar == '\''){
                if (peekChar() == '\''){
                    
                    type = SINGLE_QUOTE;
                    textBuffer.append(currentChar);
            }
                else {
                    type = CHAR;
                    textBuffer.append(currentChar);
                }
            }
            else{
                type = null;
                textBuffer.append(currentChar);
            }
            nextChar();
        }else{
            type = CHAR;
            textBuffer.append(currentChar);
            nextChar();
        }
        textBuffer.append('\'');
        text = textBuffer.toString();
        nextChar();
    }
}
