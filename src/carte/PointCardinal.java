package carte;

/**
 * Created by swag on 03/05/16.
 */
public enum PointCardinal
{
    NORD("nord"), SUD("sud"), EST("est"), OUEST("ouest");

    private final String text;

    PointCardinal(final String text)
    {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public static PointCardinal getPointCardinal(String s)
    {
        switch(((s == null)?"":s).toLowerCase())
        {
            case "nord":
                return NORD;
            case "sud":
                return SUD;
            case "est" :
                return EST;
            case "ouest" :
                return OUEST;
            default:
                return null;
        }
    }
}
