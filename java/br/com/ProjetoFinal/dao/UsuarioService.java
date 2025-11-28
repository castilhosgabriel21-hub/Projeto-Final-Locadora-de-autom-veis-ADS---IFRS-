package br.com.ProjetoFinal.dao;

import br.com.ProjetoFinal.model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UsuarioService {

    public Usuario salvar(Usuario u){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            s.persist(u);
            tx.commit();
            return u;
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public Usuario atualizar(Usuario u){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            s.merge(u);
            tx.commit();
            return u;
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public Usuario buscarPorId(Long id){
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            return s.get(Usuario.class, id);
        }
    }

    public void excluir(Long id){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            Usuario u = s.get(Usuario.class, id);
            if(u != null) s.remove(u);
            tx.commit();
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Usuario> listarTodos(){
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            return s.createQuery("from Usuario", Usuario.class).list();
        }
    }
}
