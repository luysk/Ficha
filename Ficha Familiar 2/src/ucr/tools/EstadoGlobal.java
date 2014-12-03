package ucr.tools;


/**
 * Created by Batman on 03/11/2014.
 */
public class EstadoGlobal {

    private static final EstadoGlobal INSTANCE = new EstadoGlobal();

    public static EstadoGlobal getInstance (){
        return INSTANCE;
    }
    private int codigoVivienda = -25;

    public void setCodViv(int codViv){
        codigoVivienda = codViv;
    }

    public int getCodViv(){
        return codigoVivienda;

    }

}
