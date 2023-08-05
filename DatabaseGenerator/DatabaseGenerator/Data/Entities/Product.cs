using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.SignalR;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("product")]
	public class Product
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("name")]
		public string Name { get; set; }
		[Column("description")]
		public string Description { get; set; }
		[Column("created_time")]
		public DateTime CreatedTime { get; set; }
		[Column("category_id")]
		public int CategoryId { get; set; }
		public virtual Category Category { get; set; }
		[Category("account_id")]
		public int AccountId { get; set; }
		public virtual Account Account { get; set; }
		public virtual List<Attribute> Attributes { get; set; }
		public virtual List<Photo> Photos { get; set; }
		public virtual List<Comment> Comments { get; set; }
		public virtual List<ReportMessage> ReportMessages { get; set; }
		[Column("is_deleted")]
		public bool IsDeleted { get; set; }

	}
}
