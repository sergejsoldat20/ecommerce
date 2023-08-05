using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("attribute")]
	public class Attribute
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("name")]
		public string Name { get; set; }
		[Column("type")]
		public string Type { get; set; }
		public virtual List<Product> Products { get; set; }

	}
}
