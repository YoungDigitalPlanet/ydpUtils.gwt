package eu.ydp.gwtutil.client.event;

/**
 * Base Event object.
 * 
 * @param <H>
 *            interface implemented by handlers of this kind of event
 */
@SuppressWarnings("PMD")
public abstract class EventImpl<H, T extends Enum<T>> implements Event<H, T> {
	/**
	 * Type class used to register events with an {@link PlayerEventsBus}.
	 * 
	 * @param <H>
	 *            handler type
	 */
	public static class Type<H, T extends Enum<T>> {
		private static int nextHashCode;
		private final int index;
		private final Enum<T> type;

		/**
		 * Constructor.
		 */
		public Type(Enum<T> type) {
			nextHashCode += type.ordinal();
			index = ++nextHashCode;
			this.type = type;
		}

		public Enum<T> getType() {
			return type;
		}

		@Override
		public int hashCode() {
			return index;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Type [index=");
			builder.append(index);
			builder.append(", type=");
			builder.append(type);
			builder.append("]");
			return builder.toString();
		}

		@SuppressWarnings("PMD")
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Type<?, ?> other = (Type<?, ?>) obj;
			if (type == null) {
				if (other.type != null) {
					return false;
				}
			} else if (!type.equals(other.type)) {
				return false;
			}
			return true;
		}
	}

	private Object source;

	/**
	 * Constructor.
	 */
	protected EventImpl() { // NOPMD
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.event.IEvent#getAssociatedType()
	 */
	@Override
	public abstract Type<H, T> getAssociatedType();

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.event.IEvent#getSource()
	 */
	@Override
	public Object getSource() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.event.IEvent#toDebugString()
	 */
	@Override
	public String toDebugString() {
		String name = this.getClass().getName();
		name = name.substring(name.lastIndexOf(".") + 1);// NOPMD
		return "event: " + name + ":";
	}

	/**
	 * The toString() for abstract event is overridden to avoid accidently including class literals in the the compiled output. Use {@link EventImpl}
	 * #toDebugString to get more information about the event.
	 */
	@Override
	public String toString() {
		return "An event type";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ydp.gwtutil.client.event.IEvent#dispatch(H)
	 */
	@Override
	public abstract void dispatch(H handler);

	/**
	 * Set the source that triggered this event. Intended to be called by the {@link PlayerEventsBus} during dispatch.
	 * 
	 * @param source
	 *            the source of this event.
	 * @see PlayerEventsBus#fireEventFromSource(EventImpl, Object)
	 * @see PlayerEventsBus#setSourceOfEvent(EventImpl, Object)
	 */
	protected void setSource(Object source) {
		this.source = source;
	}
}