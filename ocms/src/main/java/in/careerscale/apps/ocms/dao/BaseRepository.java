package in.careerscale.apps.ocms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepository
{

	@PersistenceContext
	protected EntityManager entityManager;

	public Object getById(Class classObject, Object id)
	{
		return entityManager.find(classObject, id);
	}
}
