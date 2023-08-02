namespace DatabaseGenerator.Data.Entities
{
	public class Attribute
	{
		public int Id { get; set; }
		public string Name { get; set; }
		public string Type { get; set; }
		public List<Product> Products { get; set; }

	}
}
