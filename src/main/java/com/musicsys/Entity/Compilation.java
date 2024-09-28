package com.musicsys.Entity;

public class Compilation {
  Integer id;
  String compilationTitle;

  public Compilation(Integer _id, String _compilationTitle) {
    this.id =_id;
    this.compilationTitle = _compilationTitle;
  }

  public void setCompilationTitle(String newTitle) {
    this.compilationTitle = newTitle;
  }

  public String getCompilationTitle() {
    return compilationTitle;
  }
}












