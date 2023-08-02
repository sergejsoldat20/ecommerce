namespace DatabaseGenerator.Data.Entities
{
	public class Account
	{
		public int Id { get; set; }
		public string FirstName { get; set; }
		public string LastName { get; set; }
		public string Username { get; set; }
		public string Role { get; set; }
		public string PhoneNumber { get; set; }
		public string Email { get; set; }
		public bool IsAccountConfirmed { get; set; }
		public string Password { get; set; }
		public string Location { get; set; }
		public virtual List<Product> Products { get; set; } = new List<Product>();
		public virtual List<Comment> Comments { get; set; } = new List<Comment>();
		public virtual List<ReportMessage> ReportMessages { get; set; } = new List<ReportMessage>();
	}
}
