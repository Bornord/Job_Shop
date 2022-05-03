package jobShop_WebProject;
/**
 * classe Status qui fait le lien entre un étudiant potentiellement candidat à une offre et une offre
 * @author arenard2
 *
 */
public class Status {

		private int idStudent;
		
		private int idOffer;
		
		private LabelStep step;

		public int getIdStudent() {
			return idStudent;
		}

		public void setIdStudent(int idStudent) {
			this.idStudent = idStudent;
		}

		public int getIdOffer() {
			return idOffer;
		}

		public void setIdOffer(int idOffer) {
			this.idOffer = idOffer;
		}

		public LabelStep getStep() {
			return step;
		}

		public void setStep(LabelStep step) {
			this.step = step;
		}
		
		
}
