namespace DatabaseGenerator.Data.Entities
{
	public class Category
	{
		public int Id { get; set; }	
		public string Name { get; set; }
		public virtual List<Attribute> Attributes { get; set; }
		public virtual List<Product> Products { get; set; }

	}
}
