package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Drawable;
import asciiPanel.Render;
import asciiPanel.TileTransformer;
import bote.game.WorldMap;
import java.awt.Color;

public class MainPage extends Page {

    private final WorldMap world;

    public MainPage(int x, int y, int w, int h) {
        world = new WorldMap(x, y, w, h);
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(50, 120, 255);
    }

    @Override
    public Color getForegroundColor() {
        return Color.WHITE;
    }

    @Override
    public Drawable[] getDefaultDraw() {
        return new Render[]{
            new Render(60, 19,
            new AsciiCharacterData(
            ImageLib.ALPHA, new Color(255, 100, 0),
            this.getBackgroundColor())
            )
        };
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 65://a
            case 37://left
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().decY();
                        c.addDraw(world.getFullDraw(c.getPlayer().getX(), c.getPlayer().getY()));
                        c.addDraw(getDefaultDraw());
                    }
                };
            case 87://w
            case 38://up
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().decX();
                        c.addDraw(world.getFullDraw(c.getPlayer().getX(), c.getPlayer().getY()));
                        c.addDraw(getDefaultDraw());

                    }
                };
            case 68://d
            case 39://right
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().incY();
                        c.addDraw(world.getFullDraw(c.getPlayer().getX(), c.getPlayer().getY()));
                        c.addDraw(getDefaultDraw());

                    }
                };
            case 83://s
            case 40://down
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().incX();
                        c.addDraw(world.getFullDraw(c.getPlayer().getX(), c.getPlayer().getY()));
                        c.addDraw(getDefaultDraw());
                    }
                };
            case 70: //f - fish
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.console("You cast out your rod...");
                        if(world.getDraw(c.getPlayer().getX(), c.getPlayer().getY()).getData().backgroundColor.getBlue()%2 == 0){
                            c.console("You catch a common tuna.");
                        } else {
                            c.console("Nothing bites.");
                        }
                    }
                };
//            case 27://esc
//                return new Command() {
//                    @Override
//                    public void exe(Controller c) {
//                        c.addAnimation(new TileTransformer() {
//                            @Override
//                            public void transformTile(int x, int y, AsciiCharacterData data) {
//                                data.foregroundColor = data.foregroundColor.darker();
//                                data.backgroundColor = data.backgroundColor.darker();
//                            }
//
//                        });
//                    }
//                };
        }
        return new Command() {
            @Override
            public void exe(Controller c) {
            }
        };
    }

    @Override
    public void playViewer() {
    }

}
