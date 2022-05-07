package jobShop_WebProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * classe Status qui fait le lien entre un étudiant potentiellement candidat à une offre et une offre
 * @author arenard2
 *
 */

@Entity
public class Status {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		private int idStudent;
		
		@ManyToOne
		private Offer offer;
		
		private LabelStep step;

		public int getIdStudent() {
			return idStudent;
		}

		public void setIdStudent(int idStudent) {
			this.idStudent = idStudent;
		}

		public Offer getOffer() {
			return offer;
		}

		public void setOffer(Offer offer) {
			this.offer = offer;
		}

		public LabelStep getStep() {
			return step;
		}

		public void setStep(LabelStep step) {
			this.step = step;
		}
		
		
}
