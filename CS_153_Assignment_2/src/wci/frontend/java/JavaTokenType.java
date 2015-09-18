package wci.frontend.java;

import java.util.HashSet;
import java.util.Hashtable;

import wci.frontend.*;

public enum JavaTokenType implements TokenType
{
	 // Reserved words.
	ABSTRACT("abstract"), DOUBLE("double"), INT("int"), LONG("long"),
	BREAK("break"), ELSE("else"), SWITCH("switch"),
	CASE("case"), ENUM("enum"), NATIVE("native"), SUPER("super"),
	CHAR("char"), EXTENDS("extends"), RETURN("return"), THIS("this"),
	CLASS("class"), FLOAT("float"), SHORT("short"), THROW("throw"),
	CONST("const"), FOR("for"), PACKAGE("package"), VOID("void"),
	CONTINUE("continue"), GOTO("goto"), PROTECTED("protected"), VOLATILE("volatile"),
	DO("do"), IF("if"), STATIC("static"),WHILE("while"),

    // Special symbols.
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), DOT("."),
    COMMA(","), SEMICOLON(";"), COLON(":"), SINGLE_QUOTE("'"),
    QUOTE("\""), NOT_EQUALS("!="),
    LESS_THAN("<"), QUESTION("?"), GREATER_THAN(">"),
    LEFT_PAREN("("), RIGHT_PAREN(")"), LEFT_BRACKET("["),
    RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"),
    UP_ARROW("^"), AND("&"), OR("|"), TILDE("~"),
    NOT("!"), AT("@"), MODULO("%"), SLASH_SLASH("//"),DOT_DOT(".."), BLANK_LINE("\n"), BACKSLASH("\\"), EQUALS("="),

    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX  = WHILE.ordinal();

    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int LAST_SPECIAL_INDEX  = EQUALS.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Pascal reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Pascal special symbols.  Each special symbol's text
    // is the key to its Pascal token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
        new Hashtable<String, JavaTokenType>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}