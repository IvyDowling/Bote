package bote;

import asciiPanel.AsciiCharacterData;
import asciiPanel.Render;
import asciiPanel.TileTransformer;
import bote.game.WorldMap;
import java.awt.Color;

public class MainPage extends Page {

    private final WorldMap world;
    private final Viewer viewer;

    public MainPage(int x, int y, int w, int h) {
        world = new WorldMap(x, y, w, h);
        viewer = new Viewer();
        viewer.addRenderArray(world.getFullDraw());
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
    public TileTransformer[] getDefaultAnimation() {
        return new TileTransformer[]{
            new TileTransformer() {
                @Override
                public void transformTile(int x, int y, AsciiCharacterData data) {
                    if (x == 60 && y == 20) {
                        data.character = ImageLib.ALPHA;
                        data.foregroundColor = Color.ORANGE;
                    }
                }
            }
        };
    }

    @Override
    public Render[][] getDefaultDraw() {
        return new Render[][]{};
    }

    @Override
    public Command pageAction(int key) {
        switch (key) {
            case 65://a
            case 37://left
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().decX();
                        world.decPlayerX();
                        c.transform(-1, 0, null);
                        c.addCol(0, world.getLeftCol());
//                        c.addDraw(world.getFullDraw());
                    }
                };
            case 87://w
            case 38://up
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().decY();
                        world.decPlayerY();
                        c.transform(0, 1, null);
                        c.addRow(0, world.getTopRow());
//                        c.addDraw(world.getFullDraw());

                    }
                };
            case 68://d
            case 39://right
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().incX();
                        world.incPlayerX();
                        c.transform(1, 0, null);
                        c.addCol(c.getScreenWidth() - 1, world.getRightCol());
//                        c.addDraw(world.getFullDraw());

                    }
                };
            case 83://s
            case 40://down
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.getPlayer().incY();
                        world.incPlayerY();
                        c.transform(0, -1, null);
                        c.addRow(c.getScreenHeight() - 1, world.getBottomRow());
//                        c.addDraw(world.getFullDraw());

                    }
                };
            case 70: //f - fish
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.console("You cast your rod...");
//                        if (world.getDraw(c.getPlayer().getX(), c.getPlayer().getY()).getData().backgroundColor.getBlue() % 2 > 100) {
//                            c.console("You catch a common tuna.");
//                        } else {
//                            c.console("Nothing bites.");
//                        }
                    }
                };
            case 27: //esc
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.console("saved");
                        c.save();
                    }
                };
            case 77: //m
                return new Command() {
                    @Override
                    public void exe(Controller c) {
                        c.transform(new TileTransformer() {
                            @Override
                            public void transformTile(int x, int y, AsciiCharacterData data) {
                                data.foregroundColor = data.foregroundColor.darker();
                                data.backgroundColor = data.backgroundColor.darker();
                            }
                        }
                        );
                    }
                };
        }
        return new Command() {
            @Override
            public void exe(Controller c) {
            }
        };
    }

    @Override
    public Viewer playViewer() {
        return viewer;
    }

}
