package org.struts.bean;

public class WordsBean {
	
	private long wordId;
	private String word;
	private int characterId;
	private int classesId;
	private String paraphrase;
	private String example;
	private int frequency;

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) { this.classesId = classesId;}

	public String getParaphrase() {
		return paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getExample() { return example;}

	public void setExample(String example) { this.example = example;}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
