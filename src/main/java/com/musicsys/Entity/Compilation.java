package com.musicsys.Entity;

public class Compilation {
    Integer id;
    String compilationTitle;

    public Compilation(Integer _id, String _compilationTitle) {
        this.id = _id;
        this.compilationTitle = _compilationTitle;
    }

    public void setCompilationTitle(String newTitle) {
        this.compilationTitle = newTitle;
    }

    public String getCompilationTitle() {
        return compilationTitle;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Compilation comp) {
            return this.id.equals(comp.getId()) && this.compilationTitle.equals(comp.getCompilationTitle());
        }
        return false;
    }
}












