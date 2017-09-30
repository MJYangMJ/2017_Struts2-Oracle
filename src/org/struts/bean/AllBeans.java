package org.struts.bean;

/**
 * Created by yang on 2017/6/24.
 */
public class AllBeans {

    private UserBean userBean;
    private WordsBean wordsBean;
    private ClassesBean classesBean;
    private CharacterBean characterBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean globalUser) {
        this.userBean = userBean;
    }

    public WordsBean getWordsBean() {
        return wordsBean;
    }

    public void setWordsBean(WordsBean wordsBean) {
        this.wordsBean = wordsBean;
    }

    public ClassesBean getClassesBean() {
        return classesBean;
    }

    public void setClassesBean(ClassesBean classesBean) {
        this.classesBean = classesBean;
    }

    public CharacterBean getCharacterBean() {
        return characterBean;
    }

    public void setCharacterBean(CharacterBean characterBean) {
        this.characterBean = characterBean;
    }
}
