package hexui_lib.patches;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import hexui_lib.CardRenderer;
import hexui_lib.interfaces.CustomCardPortrait;
import hexui_lib.util.RenderLayer;

import static hexui_lib.HexUILib.logger;


@SpirePatch(clz = SingleCardViewPopup.class, method = "renderPortrait")
public class RenderSingleCardPortraitPatch {
    @SpireInsertPatch(
            rloc=0,
            localvars={"card"}
    )
    public static SpireReturn Insert(SingleCardViewPopup view, final SpriteBatch sb, AbstractCard card) {
        if(card instanceof CustomCardPortrait){

            renderPortrait(card, sb);
            return SpireReturn.Return(null);

        }
        return SpireReturn.Continue();
    }

    private static void renderPortrait(AbstractCard card, SpriteBatch sb) {
        CustomCardPortrait customPortraitCard = (CustomCardPortrait)card;

        if (!card.isLocked){
            for(RenderLayer renderLayer : customPortraitCard.getPortraitLayers1024()){
                CardRenderer.renderHelper(card, sb, renderLayer, Settings.WIDTH / 2.0F - 512f, Settings.HEIGHT / 2.0F - 512f, true);
            }
        }else{
            //Maybe check for custom locked image here?
            for(RenderLayer renderLayer : customPortraitCard.getPortraitLayers1024()){
                CardRenderer.renderHelper(card, sb, renderLayer, Settings.WIDTH / 2.0F - 512f, Settings.HEIGHT / 2.0F - 512f, true);
            }
        }
    }
}

