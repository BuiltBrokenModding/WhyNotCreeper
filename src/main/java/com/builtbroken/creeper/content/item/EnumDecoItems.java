package com.builtbroken.creeper.content.item;

import net.minecraft.item.Item;

/**
 * Enum of decoration items
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 4/16/2018.
 */
public enum EnumDecoItems
{
    BOW_TIE_0("bow.tie.0", 0.5f,
            -0.34f * 0.5f, 0.37f, -0.5f * 0.5f,
            0, 0, 45),
    BOW_TIE_1("bow.tie.1", 0.5f,
            -0.34f * 0.5f, 0.37f, -0.5f * 0.5f,
            0, 0, 45),
    BOW_TIE_2("bow.tie.2", 0.5f,
            -0.34f * 0.5f, 0.37f, -0.5f * 0.5f,
            0, 0, 45),
    BOW_TIE_3("bow.tie.3", 0.5f,
            -0.34f * 0.5f, 0.37f, -0.5f * 0.5f,
            0, 0, 45),
    BOW_TIE_4("bow.tie.4", 0.5f,
            -0.34f * 0.5f, 0.37f, -0.5f * 0.5f,
            0, 0, 45);

    //Registry and localization suffix
    public final String name;

    //Scale for rendering
    public final float render_scale;

    //Offset for rendering
    public final float render_x;
    public final float render_y;
    public final float render_z;

    //Rotation for rendering
    public final float render_yaw;
    public final float render_pitch;
    public final float render_roll;

    //Item instance
    public Item item;

    EnumDecoItems(String name, float render_scale, float render_x, float render_y, float render_z, float render_yaw, float render_pitch, float render_roll)
    {
        this.name = name;
        this.render_scale = render_scale;
        this.render_x = render_x;
        this.render_y = render_y;
        this.render_z = render_z;
        this.render_yaw = render_yaw;
        this.render_pitch = render_pitch;
        this.render_roll = render_roll;
    }
}
