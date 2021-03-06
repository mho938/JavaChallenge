package common.model;

/**
 * Created by Razi on 12/22/2014.
 */
public class Event {
    public static final String EVENT = "event";

    protected String type; // the type of the event
    protected String objectId; // to recognize which GameObject this event is for
    protected String[] args; // arguments of the event

    public String getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public String[] getArgs() {
        return args;
    }
}
