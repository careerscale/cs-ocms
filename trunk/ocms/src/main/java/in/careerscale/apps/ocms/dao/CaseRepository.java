package in.careerscale.apps.ocms.dao;

import in.careerscale.apps.ocms.dao.model.Address;
import in.careerscale.apps.ocms.dao.model.CaseArtifact;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.Country;
import in.careerscale.apps.ocms.dao.model.DocumentType;
import in.careerscale.apps.ocms.dao.model.State;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class CaseRepository {

        @PersistenceContext
        private EntityManager entityManager;
        
        @Transactional
        public void registerCase(CaseMaster casemaster) {
                entityManager.persist(casemaster);
		entityManager.flush();
             }
       
        public void save(Address address) {
    		entityManager.persist(address);
		entityManager.flush();
    	}
        public void save(City city)
        {
        	entityManager.persist(city);
		entityManager.flush();
        }
        public City getCity(Integer id) {
    		return entityManager.find(City.class, id);

    	}
        
        public State getState(Integer id) {
    		return entityManager.find(State.class, id);
    		
    	}
        public Country getCountry(Integer id) {
    		return entityManager.find(Country.class, id);
    		
    	}
        public Address getAddress(Integer id) {
    		return entityManager.find(Address.class, id);
    		
    	}
        @SuppressWarnings("unchecked")
    	public City getCityies1() {
    		Query query =entityManager.createQuery("SELECT c FROM City c order by c.city.id,c.id"); 
    		return   (City) query.getResultList();
    		
    	}
       
       
        @SuppressWarnings("unchecked")
    	public Address getAddresses() {
    		Query query =entityManager.createQuery("SELECT a FROM Address a order by a.address.id,a.id"); 
    		return   (Address) query.getResultList();
    		
    	}
        
      /*  public City getCities(Integer cityId) {
        	 City city = entityManager.find(City.class, cityId);
    		Query query=entityManager.createQuery("select c from City c where c.city.id="+ cityId);
    		return (City) query.getResultList();
        }*/

        @SuppressWarnings("unchecked")
        public List<DocumentType> getDocumentTypes(Integer caseTypeId) {
                List<Integer> caseTypes = new ArrayList<Integer>();

                CaseType caseType = entityManager.find(CaseType.class, caseTypeId);
                while (caseType != null) {
                        caseTypes.add(caseType.getId());
                        caseType = caseType.getCaseType();
                }
                Query query = entityManager
                                .createQuery("select dt from DocumentType dt where dt.caseType.id in (:ids)");
                query.setParameter("ids", caseTypes);
                return query.getResultList();

        }
      /*  @SuppressWarnings("unchecked")
        public City getCityId(Integer cityId) {
                

                City city = entityManager.find(City.class, cityId);
                      
                Query query = entityManager
                                .createQuery("select ct from Address ct where ct.city.id in (:ids)");
                
                return (Address) query.getResultList();

        }*/
        public Object getById(Class classObject, Object id){
    		return entityManager.find(classObject, id);
    	}

	public void save(CaseArtifact artifact)
	{
		entityManager.persist(artifact);
		entityManager.flush();

	}

}

