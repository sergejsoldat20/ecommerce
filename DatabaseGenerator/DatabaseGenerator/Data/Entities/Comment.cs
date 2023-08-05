using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("comment")]
	public class Comment
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("text")]
		public string Text { get; set; }
		[Column("created_time")]
		public DateTime CreatedTime { get; set; }
		public virtual Account Account { get; set; }
		[Column("account_id")]
		public int AccountId { get; set; }
		public virtual Product Product { get; set; }
		[Column("product_id")]
		public int ProductId { get; set; }
	}
}
