using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.SignalR;

namespace DatabaseGenerator.Data.Entities
{
	public class Product
	{
		public int Id { get; set; }
		public string Name { get; set; }
		public string Description { get; set; }
		public DateTime CreatedTime { get; set; }
		public int CategoryId { get; set; }
		public virtual Category Category { get; set; }
		public int AccountId { get; set; }
		public virtual Account Account { get; set; }
		public virtual List<Attribute> Attributes { get; set; }
		public virtual List<Photo> Photos { get; set; }
		public virtual List<Comment> Comments { get; set; }
		public virtual List<ReportMessage> ReportMessages { get; set; }
		public bool IsDeleted { get; set; }

	}
}
