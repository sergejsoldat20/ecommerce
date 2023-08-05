using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("product_attribute")]
	public class ProductAttribute
	{
		[Column("product_id")]
		public int ProductId { get; set; }
		[Column("attribute_id")]
		public int AttributeId { get; set; }
		[Column("value")]
		public string Value { get; set; }
	}
}
