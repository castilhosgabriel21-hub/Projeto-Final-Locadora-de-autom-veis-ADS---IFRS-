package br.com.ProjetoFinal.ui.validators;
import java.util.regex.Pattern;
public class Validators{
  private static final Pattern EMAIL=Pattern.compile("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
  private static final Pattern TELEFONE=Pattern.compile("^\\+?\\d{8,15}$");
  private static final Pattern PLACA=Pattern.compile("^[A-Z0-9-]{5,8}$",Pattern.CASE_INSENSITIVE);
  public static boolean isEmail(String s){return s!=null&&EMAIL.matcher(s).matches();}
  public static boolean isTelefone(String s){return s!=null&&TELEFONE.matcher(s).matches();}
  public static boolean isPlaca(String s){return s!=null&&PLACA.matcher(s).matches();}
}
