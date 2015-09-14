package wci.frontend.java;

import static wci.frontend.Source.EOF;
import static wci.frontend.pascal.PascalErrorCode.INVALID_CHARACTER;
import wci.frontend.EofToken;
import wci.frontend.Scanner;
import wci.frontend.Source;
import wci.frontend.Token;
import wci.frontend.pascal.PascalTokenType;
import wci.frontend.pascal.tokens.PascalErrorToken;
import wci.frontend.pascal.tokens.PascalNumberToken;
import wci.frontend.pascal.tokens.PascalSpecialSymbolToken;
import wci.frontend.pascal.tokens.PascalStringToken;
import wci.frontend.pascal.tokens.PascalWordToken;

public class JavaScanner extends Scanner
{

	public JavaScanner(Source source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	protected Token extractToken()
	        throws Exception
	    {
	        skipWhiteSpace();

	        Token token;
	        char currentChar = currentChar();

	        // Construct the next token.  The current character determines the
	        // token type.
	        if (currentChar == EOF) {
	            token = new EofToken(source);
	        }
	        else if (Character.isLetter(currentChar)) {
	            token = new PascalWordToken(source);
	        }
	        else if (Character.isDigit(currentChar)) {
	            token = new PascalNumberToken(source);
	        }
	        else if (currentChar == '\'') {
	            token = new PascalStringToken(source);
	        }
	        else if (PascalTokenType.SPECIAL_SYMBOLS
	                 .containsKey(Character.toString(currentChar))) {
	            token = new PascalSpecialSymbolToken(source);
	        }
	        else {
	            token = new PascalErrorToken(source, INVALID_CHARACTER,
	                                         Character.toString(currentChar));
	            nextChar();  // consume character
	        }

	        return token;
	    }

	    /**
	     * Skip whitespace characters by consuming them.  A comment is whitespace.
	     * @throws Exception if an error occurred.
	     */
	    private void skipWhiteSpace()
	        throws Exception
	    {
	        char currentChar = currentChar();

	        while (Character.isWhitespace(currentChar) || (currentChar == '{')) {

	            // Start of a comment?
	            if (currentChar == '{') {
	                do {
	                    currentChar = nextChar();  // consume comment characters
	                } while ((currentChar != '}') && (currentChar != EOF));

	                // Found closing '}'?
	                if (currentChar == '}') {
	                    currentChar = nextChar();  // consume the '}'
	                }
	            }

	            // Not a comment.
	            else {
	                currentChar = nextChar();  // consume whitespace character
	            }
	        }
	    }
}