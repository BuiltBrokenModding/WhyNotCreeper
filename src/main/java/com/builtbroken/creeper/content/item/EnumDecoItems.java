package com.builtbroken.creeper.content.item;

/**
 * Enum of decoration items
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/16/2018.
 */
public enum EnumDecoItems
{
    BOW_TIE("bow.tie", 0, 0, 0, 0);

    public final String name;
    public final float render_scale;
    public final float render_x;
    public final float render_y;
    public final float render_z;

    EnumDecoItems(String name, float render_scale, float render_x, float render_y, float render_z)
    {
        this.name = name;
        this.render_scale = render_scale;
        this.render_x = render_x;
        this.render_y = render_y;
        this.render_z = render_z;
    }
}
