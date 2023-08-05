using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("category")]
	public class Category
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("name")]
		public string Name { get; set; }
		public virtual List<Attribute> Attributes { get; set; }
		public virtual List<Product> Products { get; set; }

	}
}
