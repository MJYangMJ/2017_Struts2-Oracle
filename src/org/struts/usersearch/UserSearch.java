package org.struts.usersearch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;
import org.struts.bean.*;
import org.struts.bean.ClassesBean;

import com.opensymphony.xwork2.ActionSupport;

public class UserSearch  extends ActionSupport{

	private static Log log = LogFactory.getLog(UserSearch.class);

    private UserBean globalUser;
    private WordsBean globalWords;
	private CharacterBean globalCharacter;
	private ClassesBean globalClasses;

	String n;

    public UserBean getGlobalUser() {
        return globalUser;
    }

    public void setGlobalUser(UserBean globalUser) {
        this.globalUser = globalUser;
    }

    public WordsBean getGlobalWords() {
        return globalWords;
    }

    public void setGlobalWords(WordsBean globalWords) {
        this.globalWords = globalWords;
    }

    public CharacterBean getGlobalCharacter() {
        return globalCharacter;
    }

    public void setGlobalCharacter(CharacterBean globalCharacter) {
        this.globalCharacter = globalCharacter;
    }

    public ClassesBean getGlobalClasses() {
        return globalClasses;
    }

    public void setGlobalClasses(ClassesBean globalClasses) {
        this.globalClasses = globalClasses;
    }


/**
 *
 * list of word,character and classes
 *
 */

	private List<WordsBean> wordList;

	private List<CharacterBean> characterList;

	private List<ClassesBean> classesList;

	private boolean transform;
	private int case_info;
    private String error_message;

    public List<WordsBean> getWordList() {
		return wordList;
	}

	public void setWordList(List<WordsBean> wordList) {
		this.wordList = wordList;
	}

	public List<CharacterBean> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(List<CharacterBean> characterList) {
		this.characterList = characterList;
	}

	public List<ClassesBean> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<ClassesBean> classesList) {
		this.classesList = classesList;
	}

    public boolean isTransform() {
        return transform;
    }

    public void setTransform(boolean transform) {
        this.transform = transform;
    }

	public int getCase_info() {
		return case_info;
	}

	public void setCase_info(int case_info) {
		this.case_info = case_info;
	}

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

	//connect to oracle database:scott,password:admin
	private static Connection getConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "admin";
		Connection conn = null;
		try {
			Class.forName(driver);
			//new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
			log.info("connect success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	private String userCheck() {
		log.info("user checking...");
		String name = null;
		String pass = null;
		Connection conn = getConn();
		String sql = "select * from users where username='" + globalUser.getUsername() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("username");
				pass = rs.getString("password");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(globalUser.getUsername());
		if (name == null)// 验证用户名是否存在
			return "UserNotExist";
		else if (name.equals(globalUser.getUsername()) && pass.equals(globalUser.getPassword()))// 验证登录用户名与密码是否匹配
		{
			return "success";
		}
		else
			return "error";

	}

	private UserBean find_Id(String name,String pass) {
		log.info("my query to get id...");
		Connection conn = getConn();
		String sql = "select * from users where username='"+name+"' and password='"+pass+"'";
		PreparedStatement pstmt;

		UserBean user = new UserBean();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(name);
				user.setPassword(pass);
				System.out.println("myquery: id: "+user.getId()+" name: " + name
						+ " password: " + pass);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	private List<CharacterBean> findAllCharacter() {
		log.info("get all character info ...");
		List<CharacterBean> list = new ArrayList<CharacterBean>();
		CharacterBean characterBean;
		Connection conn = getConn();
		String sql = "SELECT * FROM character";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				characterBean = new CharacterBean();
//				characterBean.setCharacterId(rs.getInt("characterId"));
				characterBean.setCharacterInfo(rs.getString("characterInfo"));
                list.add(characterBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<ClassesBean> findAllClasses() {
		log.info("get all classes info ...");
		List<ClassesBean> list = new ArrayList<ClassesBean>();
		ClassesBean classesBean;
		Connection conn = getConn();
		String sql = "SELECT * FROM classes";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				classesBean = new ClassesBean();
//				classesBean.setClassesId(rs.getInt("classesId"));
				classesBean.setClassesInfo(rs.getString("classesInfo"));
				list.add(classesBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<WordsBean> findAllWords(){
		log.info("get all words info ...");
		List<WordsBean> list = new ArrayList<WordsBean>();
		WordsBean wordsBean;
		Connection conn = getConn();
		String sql = "SELECT * FROM words";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wordsBean = new WordsBean();
				wordsBean.setWordId(rs.getLong("wordId"));
				wordsBean.setWord(rs.getString("word"));
				wordsBean.setCharacterId(rs.getInt("characterId"));
				wordsBean.setClassesId(rs.getInt("classesId"));
				wordsBean.setParaphrase(rs.getString("paraphrase"));
				wordsBean.setExample(rs.getString("example"));
				wordsBean.setFrequency(rs.getInt("frequency"));
				list.add(wordsBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private String findWordModeCheck(String chara,String cla,String wo){
	    String sql = "";
	    if(chara.equals("*")&&cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORDS";
            return sql;
        }
        else if(chara.equals("*")&&cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE WORD='"+wo+"'";
            return sql;
        }
        else if(chara.equals("*")&&!cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(chara.equals("*")&&!cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE WORD='"+wo+"' AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(!chara.equals("*")&&cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE CHARACTERID=(SELECT CHARACTERID FROM CHARACTER WHERE CHARACTERINFO='"+chara+"')";
            return sql;
        }
        else if(!chara.equals("*")&&cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE WORD='"+wo+"' AND CHARACTERID=(SELECT CHARACTERID FROM CHARACTER WHERE CHARACTERINFO='"+chara+"')";
            return sql;
        }
        else if(!chara.equals("*")&&!cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORDS WHERE CHARACTERID=(SELECT CHARACTERID FROM CHARACTER WHERE CHARACTERINFO='"+chara+"') AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(!chara.equals("*")&&!cla.equals("*")&&!wo.equals(""))
        {
	        sql = "SELECT * FROM WORDS WHERE WORD='"+wo+"' AND CHARACTERID=(SELECT CHARACTERID FROM CHARACTER WHERE CHARACTERINFO='"+chara+"') AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
	        return sql;
        }
        else return null;
    }

    private void frequencyIncrease(long wid){
	    log.info("frequency has to increase");
	    log.info("wordId="+wid+"++");
        Connection conn = getConn();
        String sql = "update words set frequency=frequency+1 where wordid='"+ wid + "'";
        int i = 0;
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);

            i = pstmt.executeUpdate();
            System.out.println("update resutl: " + i);

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<WordsBean> findResultWords(){
        log.info("get result words list info ...");
        log.info("try to find characterinfo="+globalCharacter.getCharacterInfo()+
                " classesinfo="+globalClasses.getClassesInfo()+
                " word="+globalWords.getWord());
        List<WordsBean> list = new ArrayList<WordsBean>();
        WordsBean wordsBean;
        Connection conn = getConn();
        String sql = findWordModeCheck(globalCharacter.getCharacterInfo(),globalClasses.getClassesInfo(),globalWords.getWord());
        log.info(sql);
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                wordsBean = new WordsBean();
                wordsBean.setWordId(rs.getLong("wordId"));
                wordsBean.setWord(rs.getString("word"));
//                wordsBean.setCharacterId(rs.getInt("characterId"));
//                wordsBean.setClassesId(rs.getInt("classesId"));
                wordsBean.setParaphrase(rs.getString("paraphrase"));
                wordsBean.setExample(rs.getString("example"));
                wordsBean.setFrequency(rs.getInt("frequency"));
                frequencyIncrease(wordsBean.getWordId());
                list.add(wordsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<WordsBean> findWordsByPage(int n){
		log.info("get result words list info ...");
//		log.info("try to find characterinfo="+globalCharacter.getCharacterInfo()+
//				" classesinfo="+globalClasses.getClassesInfo()+
//				" word="+globalWords.getWord());
		System.out.println("n="+n);
		List<WordsBean> list = new ArrayList<WordsBean>();
		WordsBean wordsBean;
		Connection conn = getConn();
		String sql = "select * from (select rownum no,words.* from words where rownum<=?) where no>=?";
		log.info(sql);
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,10*n+1);
			pstmt.setInt(2,10*(n-1)+1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wordsBean = new WordsBean();
				wordsBean.setWordId(rs.getLong("wordId"));
				wordsBean.setWord(rs.getString("word"));
//                wordsBean.setCharacterId(rs.getInt("characterId"));
//                wordsBean.setClassesId(rs.getInt("classesId"));
				wordsBean.setParaphrase(rs.getString("paraphrase"));
				wordsBean.setExample(rs.getString("example"));
				wordsBean.setFrequency(rs.getInt("frequency"));
				frequencyIncrease(wordsBean.getWordId());
				list.add(wordsBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

    private int wordInsert(String wo,String chara,String cla,String par,String exa){
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT INTO WORDS(word,characterId,classesId,paraphrase,example,frequency) " +
                "VALUES('"+wo+"',(SELECT CHARACTERID FROM CHARACTER WHERE CHARACTERINFO='"+chara+"')," +
                "(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')," +
                "'"+par+"','"+exa+"',1)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            log.info("try to insert");
            System.out.println(sql);
            i = pstmt.executeUpdate();
            System.out.println("insert result: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


	public String searchLogin() throws Exception {
		log.info("login.action...");
		if (userCheck().equals("success")) {
			globalUser.setId(find_Id(globalUser.getUsername(), globalUser.getPassword()).getId());
			characterList = findAllCharacter();
			classesList = findAllClasses();

            ActionContext.getContext().getSession()
                    .put("username", globalUser.getUsername());
            ActionContext.getContext().getSession()
                    .put("id", globalUser.getId());
            ActionContext.getContext().getSession()
                    .put("characterList", characterList);
            ActionContext.getContext().getSession()
                    .put("classesList", classesList);
			return SUCCESS;
		} else if (userCheck().equals("UserNotExist"))
			return "UserNotExist";
		else {
			ActionContext.getContext().getSession()
					.put("error_message","wrong Name or Password!");
			return ERROR;
		}

	}


	public String searchWords() throws Exception{
//        log.info("username="+globalUser.getUsername()+" id="+globalUser.getId());

//		wordList = findAllWords();

//		characterList = findAllCharacter();
//		classesList = findAllClasses();

//        wordList = findResultWords();
		String number = ServletActionContext.getRequest().getParameter("n");
		System.out.println("n="+number);
		wordList = findWordsByPage(Integer.parseInt(number));
		return SUCCESS;
	}

    public String addWords() throws Exception{

        boolean t = transform;

        int c = case_info;

        int i = 0;

        log.info("transform box checked:"+t);
        log.info("radio box checked:"+c);

        if(!t)
        {
            if(c!=1){
                i = wordInsert(globalWords.getWord().toLowerCase(),globalCharacter.getCharacterInfo(),globalClasses.getClassesInfo(),globalWords.getParaphrase(),globalWords.getExample());
            }
            else{
                i = wordInsert(globalWords.getWord().toUpperCase(), globalCharacter.getCharacterInfo(), globalClasses.getClassesInfo(), globalWords.getParaphrase(), globalWords.getExample());
            }
        }
        else i = wordInsert(globalWords.getWord(), globalCharacter.getCharacterInfo(), globalClasses.getClassesInfo(), globalWords.getParaphrase(), globalWords.getExample());

        if(i==1) return SUCCESS;

        else
		{
			error_message = "The word you have input is invalided";
			ActionContext.getContext().getSession()
					.put("error_message",error_message);
			return ERROR;
		}
    }
}
