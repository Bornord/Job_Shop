package jobShop_WebProject;

import javax.persistence.*;

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
		
		@ManyToOne (fetch=FetchType.EAGER)
		private Offer offer;
		//ou profil
		
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
