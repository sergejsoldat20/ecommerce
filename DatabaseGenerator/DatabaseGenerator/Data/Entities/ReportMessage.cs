using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("report_message")]
	public class ReportMessage
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("message_text")]
		public string MessageText { get; set; }
		[Column("created_time")]
		public DateTime CreatedTime { get; set; }
		[Column("is_seen")]
		public bool IsSeen { get; set; }
		[Column("account_id")]
		public int AccountId { get; set; }
		public virtual Account Account { get; set;}

		public virtual Product Product { get; set; }
		[Column("product_id")]
		public virtual int ProductId { get; set; }

	}
}
