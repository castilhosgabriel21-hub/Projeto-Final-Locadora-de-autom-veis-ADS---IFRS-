package br.com.ProjetoFinal.dao;

import br.com.ProjetoFinal.model.Veiculo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class VeiculoService {

    public Veiculo salvar(Veiculo v) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            if(v.getId() == null){
                s.persist(v);  // Novo veículo
            } else {
                s.merge(v);    // Atualiza existente
            }
            tx.commit();
            return v;
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public Veiculo buscarPorId(Long id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Veiculo.class, id);
        }
    }

    public List<Veiculo> listarTodos() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("from Veiculo", Veiculo.class).list();
        }
    }

    public void excluir(Long id) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            Veiculo v = s.get(Veiculo.class, id);
            if (v != null) s.remove(v);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
