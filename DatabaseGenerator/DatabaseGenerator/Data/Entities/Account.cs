using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("account")]
	public class Account
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("first_name")]
		public string FirstName { get; set; }
		[Column("last_name")]
		public string LastName { get; set; }
		[Column("username")]
		public string Username { get; set; }
		[Column("role")]
		public string Role { get; set; }
		[Column("phone_number")]
		public string PhoneNumber { get; set; }
		[Column("email")]
		public string Email { get; set; }
		[Column("is_account_confirmed")]
		public bool IsAccountConfirmed { get; set; }
		[Column("password")]
		public string Password { get; set; }
		[Column("location")]
		public string Location { get; set; }
		public virtual List<Product> Products { get; set; } = new List<Product>();
		public virtual List<Comment> Comments { get; set; } = new List<Comment>();
		public virtual List<ReportMessage> ReportMessages { get; set; } = new List<ReportMessage>();
	}
}
