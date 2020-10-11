package com.vtb.hackathon.automarket.model;

public class RenderPhotos {
    private Render renderMain;
    private Render main;
    private Render renderWidgetRight;
    private Render side;

    public RenderPhotos(Render renderMain, Render main, Render renderWidgetRight, Render side) {
        this.renderMain = renderMain;
        this.main = main;
        this.renderWidgetRight = renderWidgetRight;
        this.side = side;
    }

    public Render getRenderMain() {
        return renderMain;
    }

    public void setRenderMain(Render renderMain) {
        this.renderMain = renderMain;
    }

    public Render getMain() {
        return main;
    }

    public void setMain(Render main) {
        this.main = main;
    }

    public Render getRenderWidgetRight() {
        return renderWidgetRight;
    }

    public void setRenderWidgetRight(Render renderWidgetRight) {
        this.renderWidgetRight = renderWidgetRight;
    }

    public Render getSide() {
        return side;
    }

    public void setSide(Render side) {
        this.side = side;
    }
}
