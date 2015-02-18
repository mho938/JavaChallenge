package common.util;

import java.nio.charset.Charset;

/**
 * Created by Razi on 2/13/2015.
 */
public class ServerConstants {

    public static final String BLOCK_TYPE_NONE = "none";
    public static final String BLOCK_TYPE_NORMAL = "normal";
    public static final String BLOCK_TYPE_MITOSIS = "mitosis";
    public static final String BLOCK_TYPE_RESOURCE = "resource";
    public static final String BLOCK_TYPE_IMPASSABLE = "impassable";

    public static final String BLOCK_KEY_HEIGHT = "height";
    public static final String BLOCK_KEY_RESOURCE = "resource";

    public static final String CELL_KEY_ENERGY = "energy";

    public static final String GAME_OBJECT_TYPE_CELL = "cell";

    public static final String GAME_OBJECT_KEY_ID = "id";
    public static final String GAME_OBJECT_KEY_TURN = "turn";
    public static final String GAME_OBJECT_KEY_TYPE = "type";
    public static final String GAME_OBJECT_KEY_OTHER = "other";
    public static final String GAME_OBJECT_KEY_TEAM_ID = "teamId";
    public static final String GAME_OBJECT_KEY_DURATION = "duration";
    public static final String GAME_OBJECT_KEY_POSITION = "position";

    public static final String VIEW_GLOBAL = "global";
    public static final String VIEW = "view";

    public static final String INFO_KEY_TURN = "turn";
    public static final String INFO_KEY_TEAMS = "teams";
    public static final String INFO_KEY_VIEWS = "views";
    public static final String INFO_KEY_YOUR_INFO = "yourInfo";
    public static final String INFO_KEY_MAP_SIZE = "mapSize";

    public static final int TURN_TEAM_VIEW_HISTORY_DEFAULT = -10;
    public static final int TURN_WORLD_CREATION = -3;
    public static final int TURN_MAKE_MAP = -2;
    public static final int TURN_INIT = -1;

    public static final int TEAM_ID_TERMINAL_ALTERNATIVE = -2;
    public static final int TEAM_ID_ENVIRONMENT_ALTERNATIVE = -1;

    public static final int CELL_MIN_ENERGY_FOR_MITOSIS = 80;
    public static final int CELL_MAX_ENERGY = 100;
    public static final int CELL_GAIN_RATE = 15;
    public static final int CELL_DEPTH_OF_FIELD = 2;

    public static final Charset MAP_FILE_ENCODING = Charset.forName("UTF-8");

}
