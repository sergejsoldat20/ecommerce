namespace DatabaseGenerator.Data.Entities
{
	public class ReportMessage
	{
		public int Id { get; set; }
		public string MessageText { get; set; }
		public DateTime CreatedTime { get; set; }
		public bool IsSeen { get; set; }
		public int AccountId { get; set; }
		public virtual Account Account { get; set;}

		public virtual Product Product { get; set; }
		public virtual int ProductId { get; set; }

	}
}
