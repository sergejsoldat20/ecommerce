namespace DatabaseGenerator.Data.Entities
{
	public class Category
	{
		public int Id { get; set; }	
		public string Name { get; set; }
		public List<Attribute> Attributes { get; set; }
		private List<Product> Proucts { get; set; }

	}
}
