package scanner;

import java.util.ArrayList;

import exceptions.ScannerException;


import scanner.statics.Statics;
import scanner.statics.Statics.*;
import scanner.typeGroups.Group;


public class Analyzer {

	private State state = State.START;
	public InputReader inputReader;
	public ArrayList<String> tokensArray, lexemesArray;
	public ArrayList<Integer> tokensLineArray;
	public int lookAhead = 0;

	public Analyzer(InputReader inputReader) throws Exception{
		this.inputReader = inputReader;
		tokensArray = new ArrayList<String>();
		lexemesArray = new ArrayList<String>();
		tokensLineArray = new ArrayList<Integer>();
		analyze();
	}

	public void analyze() throws Exception{
		do{
			switch (state){
			case START:
				if(check(Group.WHITESPACE, State.WHITESPACE)) continue;
				if(check(Group.LETTER, State.LETTER)) continue;
				if(check(Group.DIGIT, State.DIGIT)) continue;
				if(check('<', State.LT)) continue;
				if(check('>', State.GT)) continue;
				if(check('=', State.EQ)) continue;
				if(check('!', State.NOT)) continue;
				if(check('&', State.AND)) continue;
				if(check('|', State.OR)) continue;
				if(check('/', State.SLASH)) continue;
				if(check('.', State.DOT)) continue;
				if(check('*', "*")) continue;
				if(check('+', "+")) continue;
				if(check('-', "-")) continue;
				if(check('%', "%")) continue;
				if(check('{', "{")) continue;
				if(check('}', "}")) continue;
				if(check('[', "[")) continue;
				if(check(']', "]")) continue;
				if(check('(', "(")) continue;
				if(check(')', ")")) continue;
				if(check(';', ";")) continue;
				if(check(',', ",")) continue;
				if(check(Group.EOF, "")) break;
				break;
			case LT:
				if(check('=', "<=")) continue;
				if(check(Group.ALL, "<", true)) continue;
				break;
			case GT:
				if(check('=', ">=")) continue;
				if(check(Group.ALL, ">", true)) continue;
				break;
			case EQ:
				if(check('=', "==")) continue;
				if(check(Group.ALL, "=", true)) continue;
				break;
			case NOT:
				if(check('=', "!=")) continue;
				if(check(Group.ALL, "!", true)) continue;
				break;
			case AND:
				if(check('&', "&&")) continue;
				break;
			case OR:
				if(check('|', "||")) continue;
				break;
			case SLASH:
				if(check('/', State.COMMENT)) continue;
				if(check('*', State.COMMENT_MULTILINE)) continue;
				if(check(Group.ALL, "/", true)) continue;
				break;
			case COMMENT:
				if(check('\n', State.START)){
					inputReader.reset();
					continue;
				}
				if(check(Group.ALL, State.COMMENT)) continue;
				break;
			case COMMENT_MULTILINE:
				if(check('*', State.COMMENT_MULTILINE_STAR)) continue;
				if(check(Group.ALL, State.COMMENT_MULTILINE)) continue;
				break;
			case COMMENT_MULTILINE_STAR:
				if(check('/', State.START)){
					inputReader.reset();
					continue;
				}
				if(check('*', State.COMMENT_MULTILINE_STAR)) continue;
				if(check(Group.ALL, State.COMMENT_MULTILINE)) continue;
				break;
			case LETTER:
				if(check(Group.LETTER, State.LETTER)) continue;
				if(check(Group.DIGIT, State.LETTER)) continue;
				if(check('_', State.LETTER)) continue;
				findKeywordOrID();
				continue;
			case DIGIT:
				if(check(Group.DIGIT, State.DIGIT)) continue;
				if(check('.', State.DIGIT_DOT)) continue;
				if(check(Group.ALL, "int_num", true)){
					inputReader.reset();
					continue;
				}
				break;
			case DIGIT_DOT:
				if(check(Group.DIGIT, State.DIGIT_DOT)) continue;
				if(check(Group.ALL, "float_num", true)){
					inputReader.reset();
					continue;
				}
				break;
			case DOT:
				if(check(Group.DIGIT, State.DIGIT_DOT)) continue;
				break;
			case WHITESPACE:
				if(check(Group.WHITESPACE, State.WHITESPACE)) continue;
				if(check(Group.ALL, "", true)) continue;
			default:
				break;
			}
			throw new ScannerException(inputReader.generateErrorMessage(), inputReader.getErrorLine());
		}while(inputReader.current()!=Statics.EOF);
		
		addToken("$", -1);
	}
	private void findKeywordOrID() {
		String lexeme = inputReader.getLexeme();
		int line = inputReader.getLexemeLine();
		for(int i=0; i<Statics.KeywordList.length; i++)
			if(lexeme.equals(Statics.KeywordList[i])){
				addToken(lexeme, line);
				state = State.START;
				inputReader.reset();
				return;
			}
		addToken("id", line);
		inputReader.reset();
		state = State.START;
	}

	private boolean check(Object a, Object s){
		return check(a, s, false);
	}
	private boolean check(Object tgroup, Object s, boolean retract) {
		if(!(tgroup.equals(inputReader.current())))
				return false;
		if(tgroup.equals(inputReader.current())){
			if(s instanceof State){
				state = (State) s;
				if(retract)
					inputReader.moveBackward();
				inputReader.moveForward();
			}
			else{
				if(!((String)s).equals("")){
					addToken((String)s, inputReader.getLexemeLine());
				}
				state = State.START;
				if(retract)
					inputReader.moveBackward();
				inputReader.moveForward();
				inputReader.reset();
			}
		}
		return true;
	}
	public void addToken(String token, Integer line){
		this.tokensArray.add(token);
		this.tokensLineArray.add(line);
		this.lexemesArray.add(inputReader.getLexeme());
	}
	public ArrayList<String> getTokensArray() {
		return this.tokensArray;
	}
	public String getToken(){
		return tokensArray.get(lookAhead);
	}
	public boolean hasNextToken(){
		return lookAhead<=tokensArray.size()-1;
	}
	public int getLine(){
		return tokensLineArray.get(lookAhead);
	}
	public String getLexeme(){
		return lexemesArray.get(lookAhead);
	}
	public void increaseLookAhead(int num){
		lookAhead+=num;
	}

	public void decreaseLookAhead(int num) {
		lookAhead-=num;
		// TODO Auto-generated method stub
		
	}

	public void destroyTokenUnderLookAhead() {
		this.tokensArray.remove(lookAhead);
		this.tokensLineArray.remove(lookAhead);
		this.lexemesArray.remove(lookAhead);
	}
}
